package com.example.hrms.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "system_users")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Data
@NoArgsConstructor
public class SystemUser extends User {


    @Column(name = "position")
    @NotEmpty
    @NotNull
    String position;

}
