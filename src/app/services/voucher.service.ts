import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface Voucher {
    code: string;
    amount: number;
    signature: string;
    createdAt: string;
}

@Injectable({
    providedIn: 'root'
})
export class VoucherService {
    private vouchers: Voucher[] = [];
    private readonly SECRET_KEY = 'MY_SUPER_SECRET_KEY'; // In a real app, this should be backend-side or non-exposed

    constructor(private http: HttpClient) { }

    async generateVoucher(amount: number): Promise<Voucher> {
        // Generate a 14-character alphanumeric string (uppercase)
        // Excluding 'O', '0', 'I', '1' to avoid confusion
        const chars = 'ABCDEFGHJKLMNPQRSTUVWXYZ23456789';
        let code = '';
        for (let i = 0; i < 16; i++) {
            code += chars.charAt(Math.floor(Math.random() * chars.length));
        }

        // As per requirement, no "GIFT" prefix and no amount in the code string itself
        // just the 14 chars.

        const signature = await this.signCode(code);

        const voucher: Voucher = {
            code,
            amount,
            signature,
            createdAt: new Date().toISOString()
        };

        // Save to backend
        this.http.post<Voucher>('/vouchers', voucher).subscribe({
            next: (v: Voucher) => console.log('Voucher saved:', v),
            error: (e: any) => {
                console.error('Error saving voucher:', e);
                if (e.error) {
                    console.error('Server error details:', e.error);
                }
            }
        });

        this.vouchers.push(voucher);
        return voucher;
    }

    getVouchers(): Voucher[] {
        return this.vouchers;
    }

    async verifyVoucher(code: string, signature: string): Promise<boolean> {
        const recalculatedSignature = await this.signCode(code);
        return recalculatedSignature === signature;
    }

    // Generates a 4-digit PIN based on the code and secret key
    private async signCode(code: string): Promise<string> {
        const encoder = new TextEncoder();
        const data = encoder.encode(code + this.SECRET_KEY);
        const hashBuffer = await crypto.subtle.digest('SHA-256', data);

        // Use the first 4 bytes to generate an integer
        const hashArray = new Uint8Array(hashBuffer);
        const dataView = new DataView(hashArray.buffer);
        const num = dataView.getUint32(0); // big-endian by default

        // Modulo 10000 to get a 4-digit number
        const pin = num % 10000;
        return pin.toString().padStart(4, '0');
    }

    downloadVouchers() {
        this.http.get('/vouchers/generate-pdf', { responseType: 'blob' }).subscribe({
            next: (blob: Blob) => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = 'vouchers.pdf';
                a.click();
                window.URL.revokeObjectURL(url);
            },
            error: (e: any) => console.error('Error downloading PDF:', e)
        });
    }
}
