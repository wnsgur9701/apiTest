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
        String paymentToken = "kSAops9Zwhos8hSTSeLTUX76vwWxr99AQDV98EpxnvD06VoXDh78OCKkH3iURpYEEJdCfzczKdbRxnnQreRt8CqlPLaHtKXuHzqFiCm+8NDQGGAvj2wR6rtwxtOzVo91";
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
        String body = "eyJhbGciOiJQUzI1NiJ9.ZXlKbGJtTWlPaUpCTWpVMlIwTk5JaXdpWVd4bklqb2lVbE5CTFU5QlJWQWlmUS5NLVo5dkNzdTFnenpDNDZuV1dvckljWlhsSjFUcExRSUFyNzF1TVh5TkozREZFalJqMmRHTzVzWGlrSHgtV0dFUHBhdEpVclBncERsYjRSZW1qbFNHa3hsQ19PWExCZTYzRkFsWkd0YUM3V0hnd2V6NkZ6VHNTLXdybjJXQk95Slplc2RtSy1USU9yZEZ5ZFVLR0FHYUZyZ0dVLTRFc3cyWVRjR0FTVVBsbkhQUElLaVVGVlZwdlZkeDViZG80OE1KZ21MN1RtRjg5b1pNLXlTVmx0dnFuZDBIZ2dwRXhfZ3JHWGtwcmptMXZIdVFVbmZvRGlNcXF2a3AxN0tKRVBDS0dtbGo1b2lRZUhqZVpZbjNHWnJibjB0WnBkbUh0VWV5bjljb0tBanUteDJhVVZ5bGh2cEZPV3FnZUxYSjJWQTRtQ2FINkZDWVpoaktHQVBmVGJRS3cuZXBESWxyTVF6WXNIeFI1My5tZk5qZWxFTkFfMm10cTJzYXU5a2ZlNnJMQnNoTnZZRkhrZ2RjVmNwNDRDTXpPSFVkMnUwbXlqRDdPWGJSTUoxZGM5d3h5MGZrWHN4STM0aEJJMUc4NmZEeU1ORjlERFNTbktXX3NhQnI2ZV9QbVpqdjh2aTlxdDlhTGFxMnVoWWd3bXBBWEVkTHdoQjktZ25mY2l6UVZ3X1c1aVJnbnJjU0FZdzV1ZU1uN2N6b0RxRHRhMVhSZHdBblNfY3pOWXIxdDF2TUhvcE8wZjdabFRQaGlDNU1sVERCWm9xVDBiem9rRWdxbzAzUHRrbDJGWFk5dGJjLUZYQVNhNEd1MXlNRmFCT0FvQnVnOUlNRGk0LnJneEJlWEFvWTM0OXU1dTd1UUFnVFE.lJYD-taayuAfYWCtu1YrVUf8kPFiGxAaslLsTBree6PSJAwds8j-M1iNCwJWa0SbZe48n_Lr_Eg-nqrvmCeRlMYy-DrdfIEMxd0GPmtsuNn56wImSIwqlb0t53qcL3aG1DnOzSSGw71iZv5SAzMyRxV3ZfANci6YKK8vQ-s_tuoM5Z6lwsuzbh3-ojKDwA10P7OLB2wfQMkEY3lxDwxPyRa2yOPd8xIVEawwLHzuf4yJWiES-pdjsv0NpCOUQA1wB67_9E1AnWEwajE43Cp5CcUFgKbu8gOg-fL3jqE9iXahwB2OUKloVv7jn3-sTSiVirLk6C6-vcVN5AvmptgpCU2o3laJQs4OAUGq87iUtEDJTDCHdcQhTHlDobbuxeZlvmRWcz73NPozaHdvp2Rn2mebglPTigkySzshWismiP-PC5KV4dh7jvnb5Tlm87lA373NeqUtt3W5Le3SFtfxcbMbtBfOeh7clWsvn3KV-fgcb1aaKss4I23-FMBwXg8NaHiowbaXwEu6iMV5l3cvQfPCT9FP6ebms5PQM8ClI9IlLsCImxtPKnT93EnqLOwUyjU8XfQWWN9WbU3hD8MveBDIHF--ZdbaEjbQd2UZckxeKncX1NACzthWfZBrsh0FqgV2X2Zf_xJzrSJnmgzpLM4KYYC7osVmHbFSYq0gqA4"; // String |
        try {
            String result = apiInstance.paymentAction20ActionPost(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InquiryApi#paymentAction20ActionPost");
            e.printStackTrace();
        }



    }
}

