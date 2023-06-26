package com.notimplement.happygear.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.notimplement.happygear.model.wallet.CustomerMemberShipDto;
import com.notimplement.happygear.model.wallet.LoginFormDto;
import com.notimplement.happygear.model.wallet.RequestAdditionDto;
import com.notimplement.happygear.model.wallet.RequestSubtractionDto;
import com.notimplement.happygear.util.TokenUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/partner/api")
@SecurityRequirement(name = "Authorization")
public class WalletApi {

    @GetMapping("/members/information")
    public ResponseEntity<?> getWalletByCustomerId(Long customerId, HttpServletRequest request) {
        String token = TokenUtil.getBearerToken(request);
        String url = "https://swd-back-end.azurewebsites.net/partner/api/members/information?customerId=" + customerId;
        
        WebClient.Builder builder = WebClient.builder();
        CustomerMemberShipDto customerMemberShipDto = builder.build()
                .get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(CustomerMemberShipDto.class)
                .block();
        return ResponseEntity.ok(customerMemberShipDto);
    }

    @PostMapping("/programs/token")
    public ResponseEntity<?> getPartnerToken(@RequestBody LoginFormDto loginFormDto ){
        String url = "https://swd-back-end.azurewebsites.net/partner/api/programs/token";
        WebClient.Builder builder = WebClient.builder();

        String token = builder.build()
                .post()
                .uri(url)
                .bodyValue(loginFormDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(token == null)
            return ResponseEntity.badRequest().body("Invalid username or password");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/requests/subtraction")
    public ResponseEntity<?> requestSubtraction(@RequestBody RequestSubtractionDto subtraction, HttpServletRequest request){
        String token = TokenUtil.getBearerToken(request);
        String url = "https://swd-back-end.azurewebsites.net/partner/api/requests/subtraction";
        WebClient.Builder builder = WebClient.builder();

        String result = builder.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .bodyValue(subtraction)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(result == null)
            return ResponseEntity.badRequest().body("Invalid request");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/requests/addition")
    public ResponseEntity<?> requestAddition(@RequestBody RequestAdditionDto addition, HttpServletRequest request){
        String token = TokenUtil.getBearerToken(request);
        String url = "https://swd-back-end.azurewebsites.net/partner/api/requests/addition";
        WebClient.Builder builder = WebClient.builder();

        String result = builder.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .bodyValue(addition)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(result == null)
            return ResponseEntity.badRequest().body("Invalid request");
        return ResponseEntity.ok(result);
    }
}
