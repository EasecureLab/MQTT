package com.wsn.nac.publish.service;


import com.wsn.nac.publish.entity.pushBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PushService {


    final private RestTemplate restTemplate;

    public void pushToBroker(pushBody body){

        String url = "http://121.4.39.153:8090/api/v4/mqtt/publish";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<pushBody> entity = new HttpEntity<>(body,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,entity,String.class);
        String response = responseEntity.getBody();
//        System.out.println(response);
    }

}
