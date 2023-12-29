package com.kirana.kirana.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.kirana.kirana.models.Kirana;
import com.kirana.kirana.repo.KiranaRepository;

@RestController
@RequestMapping("/transaction")
public class KiranaController {
    double UsdRate;

    void UsdRatefind() {
        try {
            var url = "https://api.fxratesapi.com/latest";
            var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            var client = HttpClient.newBuilder().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            JsonObject jsonObject = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
            UsdRate = jsonObject.get("rates").getAsJsonObject().get("INR").getAsDouble();
        } catch (Exception e) {
            System.out.println(e);
        }
        // map to GSON objects
    }

    @Autowired
    KiranaRepository kiranaRepository;

    @PostMapping("/")
    public ResponseEntity<?> addtranscation(@RequestBody Kirana kirana) {
        Kirana save = this.kiranaRepository.save(kirana);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/")
    public ResponseEntity<?> gettranscations() {
        return ResponseEntity.ok(this.kiranaRepository.findAll());
    }

    @GetMapping("/user_id/")
    public ResponseEntity<?> getByName(@RequestHeader String cust_id) {
        List<Kirana> ls = kiranaRepository.findByCust_id(cust_id);
        if (ls.isEmpty()) {
            System.out.println("DATA NOT FOUND");
        }
        return ResponseEntity.ok(ls);
    }

    @GetMapping("/user_id_and_date/")
    public ResponseEntity<?> getByNameAndDate(@RequestHeader String cust_id, @RequestHeader String date) {
        System.out.println(date);
        List<Kirana> ls = kiranaRepository.findByCust_idandDate(cust_id, date);
        if (ls.isEmpty()) {
            System.out.println("DATA NOT FOUND");
        }
        return ResponseEntity.ok(ls);
    }

    @GetMapping("/user_idT/")
    public ResponseEntity<Map<String, String>> getByNameT(@RequestHeader String cust_id) {
        UsdRatefind();
        List<Kirana> ls = kiranaRepository.findByCust_idT(cust_id);
        if (ls.isEmpty()) {
            System.out.println("DATA NOT FOUND");
        }
        double credit_amount = 0;
        double debit_amount = 0;
        for (Kirana k : ls) {
            System.out.println(k.getMethod());
            if (k.getMethod().equals("DEBIT")) {
                if (k.getCurrency().equals("USD")) {
                    debit_amount += UsdRate * k.getPrice() * k.getQuantity();
                } else {
                    debit_amount += k.getPrice() * k.getQuantity();
                }
            } else if (k.getMethod().equals("CREDIT")) {
                if (k.getCurrency().equals("USD")) {
                    credit_amount += UsdRate * k.getPrice() * k.getQuantity();
                } else {
                    credit_amount += k.getPrice() * k.getQuantity();
                }

            }
        }
        Map<String, String> response = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat("#0.00");
        response.put("cust_id", cust_id);
        response.put("total_profit_in_inr", "Rs. " + df.format(credit_amount - debit_amount));
        response.put("total_profit_in_usd", df.format((credit_amount - debit_amount) / UsdRate) + " $");
        response.put("total_debit_in_inr", "Rs. " + df.format(debit_amount));
        response.put("total_debit_in_usd", df.format((debit_amount) / UsdRate) + " $");
        response.put("total_credit_in_inr", "Rs. " + df.format(credit_amount));
        response.put("total_credit_in_usd", df.format(credit_amount / UsdRate) + " $");

        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/user_id_and_dateT/")
    public ResponseEntity<Map<String, String>> getByCust_idandDateT(@RequestHeader String cust_id,
            @RequestHeader String date) {
        UsdRatefind();
        List<Kirana> ls = kiranaRepository.findByCust_idandDateT(cust_id, date);
        if (ls.isEmpty()) {
            System.out.println("DATA NOT FOUND");
        }
        double credit_amount = 0;
        double debit_amount = 0;
        for (Kirana k : ls) {
            if (k.getMethod().equals("DEBIT")) {
                if (k.getCurrency().equals("USD")) {
                    debit_amount += UsdRate * k.getPrice() * k.getQuantity();
                } else if (k.getCurrency().equals("INR")) {
                    debit_amount += k.getPrice() * k.getQuantity();
                }
            } else if (k.getMethod().equals("CREDIT")) {
                if (k.getCurrency().equals("USD")) {
                    credit_amount += UsdRate * k.getPrice() * k.getQuantity();
                } else if (k.getCurrency().equals("INR")) {
                    credit_amount += k.getPrice() * k.getQuantity();
                }
            }
        }
        Map<String, String> response = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat("#0.00");
        response.put("cust_id", cust_id);
        response.put("Date", date);
        response.put("total_profit_in_inr", "Rs. " + df.format(credit_amount - debit_amount));
        response.put("total_profit_in_usd", df.format((credit_amount - debit_amount) / UsdRate) + " $");
        response.put("total_debit_in_inr", "Rs. " + df.format(debit_amount));
        response.put("total_debit_in_usd", df.format((debit_amount) / UsdRate) + " $");
        response.put("total_credit_in_inr", "Rs. " + df.format(credit_amount));
        response.put("total_credit_in_usd", df.format(credit_amount / UsdRate) + " $");

        return ResponseEntity.accepted().body(response);
    }

}
