import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VoucherService, Voucher } from '../../services/voucher.service';

@Component({
    selector: 'app-vouchers',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './vouchers.component.html',
    styleUrls: ['./vouchers.component.css']
})
export class VouchersComponent {
    amount: number | null = null;
    generatedVoucher: Voucher | null = null;

    constructor(private voucherService: VoucherService) { }

    async generateCode() {
        if (this.amount) {
            this.generatedVoucher = await this.voucherService.generateVoucher(this.amount);
        }
    }

    downloadVouchers() {
        this.voucherService.downloadVouchers();
    }
}
