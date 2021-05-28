package com.example.hrms.entities.concrete;

import com.example.hrms.entities.abstracts.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "system_users")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Data
@NoArgsConstructor
public class SystemUser extends User {


    @Column(name = "position")
    String position;

}
