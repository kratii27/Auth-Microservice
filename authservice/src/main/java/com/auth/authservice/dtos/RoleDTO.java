package com.auth.authservice.dtos;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    private UUID id = UUID.randomUUID();
    private String name;
}
