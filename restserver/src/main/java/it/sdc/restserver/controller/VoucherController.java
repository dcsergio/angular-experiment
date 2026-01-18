package it.sdc.restserver.controller;

import it.sdc.restserver.entity.Voucher;
import it.sdc.restserver.service.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow requests from Angular frontend
public class VoucherController {

    private final VoucherRepository voucherRepository;

    @PostMapping
    public Voucher saveVoucher(@RequestBody Voucher voucher) {
        // Ensure ID is null so Mongo generates it (if not provided)
        // or just let Mongo handle it.
        // We might want to set createdAt if it's not passed, but the frontend sends it.
        return voucherRepository.save(voucher);
    }

    @GetMapping
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAllByOrderByCreatedAtDesc();
    }
}
