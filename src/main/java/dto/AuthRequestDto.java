package dto;

import jdk.jshell.spi.SPIResolutionException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlSeeAlso;

@Setter
@Getter
@ToString
@Builder
public class AuthRequestDto {
    private String username;
    private String password;
}
