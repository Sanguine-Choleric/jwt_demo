package com.tb.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue
    // (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

//    private String roles;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

//    @ManyToMany
//    private List<Role> roles;

//    public void setRoles(String roles) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<List<Role>> jacksonTypeReference = new TypeReference<List<Role>>() {};
//        List<Role> roleList = objectMapper.readValue(roles, jacksonTypeReference);
//        this.roles = roleList;
////        this.roles = roles;
//    }
}
