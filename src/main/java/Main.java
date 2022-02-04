import io.swagger.client.ApiException;
import io.swagger.client.api.InquiryApi;
import io.swagger.client.api.PaymentApi;
import io.swagger.client.model.*;

public class Main {

    public static void main(String[] args) {

        /**
         * 1. payment token
         */
        PaymentApi paymentTokenApiInstance = new PaymentApi();
        String payload = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhbW91bnQiOjUwLjAsIm1lcmNoYW50SUQiOiI3MDI3MDIwMDAwMDE2NzAiLCJkZXNjcmlwdGlvbiI6ImRvZ2Vkb2dlZG9nZSIsImludm9pY2VObyI6IlNHRDg0ODQyMjYyIiwiY3VycmVuY3lDb2RlIjoiU0dEIiwicGF5bWVudENoYW5uZWwiOlsiQUxJUEFZIl19.SB4XXVClvFAKdKlMAAW29nStqABujwoqyVvCSfuPMS4";
        JWTPayloadRequest jwtPayloadRequest = new JWTPayloadRequest();
        jwtPayloadRequest.setPayload(payload);

        try {
            JWTPayloadResponse jwtPayloadResponse = paymentTokenApiInstance.paymentToken(jwtPayloadRequest);
            System.out.println("jwtPayloadResponse = " + jwtPayloadResponse);

        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#paymentToken");
            e.printStackTrace();
        }

        /**
         * 2. do payment
         */
        PaymentApi paymentApiInstance = new PaymentApi();

        DoPaymentRequestPaymentCode code = new DoPaymentRequestPaymentCode();
        code.setChannelCode("ALIPAY");

        DoPaymentRequestPaymentData data = new DoPaymentRequestPaymentData();
        data.setName("nick");
        data.setEmail("nick@eximbay.com");

        DoPaymentRequestPayment payment = new DoPaymentRequestPayment();
        payment.setCode(code);
        payment.setData(data);

        DoPaymentRequest doPaymentRequest = new DoPaymentRequest();
        String paymentToken = "kSAops9Zwhos8hSTSeLTUbyq/ly92pQVu9RWCokB1lh+GxPgaVsszBk5jGGUoijEVlZOn9dh6SAv6rMtyzyFCcyOsxVibYG1TzEG9FRwU5JJc8HY7wlQxDgDoc6RzTOB";
        doPaymentRequest.setPaymentToken(paymentToken);
        doPaymentRequest.setPayment(payment);
        doPaymentRequest.setClientID("E380BEC2BFD727A4B6845133519F3AD7");
        doPaymentRequest.setLocale("en");

        try {
            DoPaymentResponse doPaymentResponse = paymentApiInstance.payment41PaymentPost(doPaymentRequest);
            System.out.println("doPaymentResponse = " + doPaymentResponse);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#payment41PaymentPost");
            e.printStackTrace();
        }

        /**
         * 3. Inquiry
         */

        InquiryApi apiInstance = new InquiryApi();
        String body = "eyJhbGciOiJQUzI1NiJ9.ZXlKbGJtTWlPaUpCTWpVMlIwTk5JaXdpWVd4bklqb2lVbE5CTFU5QlJWQWlmUS5Lcm1va3BrRzY5NlBwS2t0NWtzVWF4Xzk0M2hqaGtGRzY4ek5XbXYtUlhUeVJBeGZwTGJ0MkVfWkVKVUdQWk1yQmlaRm1rUWZfZkV5WHZCZGJXV3F3ckxvakZ2OWpJcFFaOEJQZmUyY0UzUE1WSHB6U1pKcVQ2c3FwOXg5VWgxSXBNN181SmUtNG1XUWRkeUpCQ0xHZUZJVlV2OVBWT1VVczIxc3ZCSTZnbmttVzVXY0Eza1BMYTdsZl8yejB3WEVONE5Bb210QnlZOVJWNlJjRjJJVmZqS051UzVMaVVOc0pGdVNoS2w3ZGpMNndDZkVrT00tVFUzaTY5VlRtTWdWSkJBakx6TE4wWlZrMUJWNFQtekRiVW5OVG1ydFlxMG52dWg1dlhmWGF0WEtpR3RQRko2MkJBZHFENEdmRXZZVjZrVWtTLWgwOGlWX3djSzBkYnR4WmcuNWRzbWdyc0VBdGJhZWpPMS5LaE1MX24zcjVfWGExVFM3bmhTWHFNblU3ZDRMTUF6MWF4RzhJU0pnVFZ0c3ZDR2ZtWUUzc3BTdUt2RDlXYVVta1NSblJvWnl3Y2Etdk1mY2Jld0RKbVBYTGI5MVZOQXlCWXdUNTJWQzRSNkxraXJPXzVESDJKamY2NGVDYTNmWTMtTS1HZTdVaWdOUE1JSU1TaUUwZ3g2eExRazh4S3NuY1BGeFdHbTNSX0E0cDRBOTdzSmVWRFpiSGhwZ2NKZzZGRU80SmV2eFJUNDhZeThwMlUtRFc4OXJvS0xxVDktdUMzMUZXdlhVX0pCbk4zS0xXX0dfeWdLZ2tCU3FWUUMtTFh3azduSUQtMnM5TDZtcEJkNTE2TjBqb0VsU2Y5YW4uUTlrWjVFN0sxQTdReUR6VmpMNGVSZw.bk5X2rr0pMpKsV8qa0eRQMi6haq6G2RAwpBWnHrF3M131EbuCZg8CPCZ_8XD4q8Rer4CurDHra75c8NnOQB0qDiV6Fp_GGrd-lJ6WvZi2ChKC5jDUwANnLXEhPwb2uBH2q9elt5gIRFvhEirNHu7fOUNjn1GXAw4NRi57TgyKwWL9WZjn47hII_UHhxeSpqHd6k7qxla6Tqgf7-ZfqcKIUHqn1-Ce_mI4WbHDKiSh2IeK4cmkO2I2ztF9NDMpO1of3ObTzQEDGSEQVugL9yilejMyhNQ96NOBpX_kNN5keVGxsyP_ENMrTJ_fjsfpIJzw4NKjuMn-yTZ8FfKBGXWeBXHsL8-8LjrxDsmW8SIvvBSj259H3Wv4qCdEgRCMe9hF-iD3KLlapTZ3K5NpnUseobuzBDxrsqTsraZUdCYxXacRdX9JzYV0AK8xNBYZv-K2VsMtGXOuZJwtkcMOBlfaun9KdHtaVfqSNWYJAE4-tjlsh7elZOv6URWkVpLZ7nLwZQJwuoVm6QrtVBdg-vpTn0EKJFe04DN5dGjsfL3A2Niwj9_gaubOAOTsQ9es3i_tivLcZMc6YbCnZgeJmZOSYXMT8cuRz7z1Hq-ran-uv7m8vg4-ZEG7BoFb0F9JD5ZVCBdC2XeADKadIrU4Ab2K0FisjtSBcfCp1dH_BkTyu8"; // String |
        try {
            String result = apiInstance.paymentAction20ActionPost(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InquiryApi#paymentAction20ActionPost");
            e.printStackTrace();
        }



    }
}

