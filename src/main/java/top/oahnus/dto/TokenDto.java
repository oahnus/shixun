package top.oahnus.dto;

import lombok.Data;
import top.oahnus.service.TokenService;

import java.util.Date;

/**
 * Created by oahnus on 2017/2/26.
 * Token Dto
 */
@Data
public class TokenDto {
    // token
    private String token;
    // 创建时间
    private Date createAt;
    // 持续时间
    private Date expire;

    public TokenDto(){}

    public TokenDto(String token){
        this.token = token;
        this.createAt = new Date();
        this.expire = new Date(this.createAt.getTime() + TokenService.expire * 86400000);
    }
}
