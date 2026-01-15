import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VoucherService } from '../../services/voucher.service';

@Component({
    selector: 'app-verify',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './verify.component.html',
    styleUrls: ['./verify.component.css']
})
export class VerifyComponent {
    code: string = '';
    signature: string = '';
    verificationResult: boolean | null = null;

    constructor(private voucherService: VoucherService) { }

    async verify() {
        if (this.code && this.signature) {
            this.verificationResult = await this.voucherService.verifyVoucher(this.code, this.signature);
        }
    }
}
