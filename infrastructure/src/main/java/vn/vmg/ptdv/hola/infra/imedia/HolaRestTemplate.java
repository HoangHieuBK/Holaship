package vn.vmg.ptdv.hola.infra.imedia;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.util.Arrays;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;


public class HolaRestTemplate {
    private static final String P_DATA = "data";

    public SSORequestResponse post(String url, String payload) throws SecurityDESException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        JsonParser parser = new JacksonJsonParser();
        Map<String, Object> dataAsJson = parser.parseMap(result);
        String dataEncrypted = (String) dataAsJson.get(P_DATA);
        String dataPure = TripleDES.decrypt(KEY3DES, dataEncrypted);
        System.out.println(dataAsJson);

        return new SSORequestResponse(dataAsJson);
    }

    public SSORequestResponse postUpdate(String url, String payload) throws SecurityDESException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        JsonParser parser = new JacksonJsonParser();
        Map<String, Object> dataAsJson = parser.parseMap(result);
        String dataEncrypted = (String) dataAsJson.get(P_DATA);
        String dataPure = TripleDES.decrypt(KEY3DES, dataEncrypted);
        System.out.println(dataAsJson);

        return new SSORequestResponse(dataAsJson);
    }

    public TechConfirmInfoResponse iMediaTechConfirmInfo(String url, String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        JsonParser parser = new JacksonJsonParser();
        Map<String, Object> dataAsJson = parser.parseMap(result);
        return new TechConfirmInfoResponse(dataAsJson);
    }
}
