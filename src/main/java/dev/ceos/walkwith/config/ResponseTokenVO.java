
package dev.ceos.walkwith.config;

import lombok.Data;

@Data
public class ResponseTokenVO {
    private String token;
    private String firstYN;

    public ResponseTokenVO(String token, String firstYN) {
        this.token = token;
        this.firstYN = firstYN;
    }
    public ResponseTokenVO(){ }
}
