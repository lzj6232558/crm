package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyy-MM-dd" )
    private Date inputtime;

    private boolean state;

    private boolean admin;

    private List<Role> roles;
}