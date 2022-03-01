package com.overit.dataversioning.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "XPRJAUSER")
@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AUser implements Serializable {

    @Id
    @Column(name = "XPRJAUSEID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUSER_SEQ")
    @SequenceGenerator(name = "AUSER_SEQ", sequenceName = "SXPRJAUSER", allocationSize = 1)
    private Long id;
    @Column(name = "XPRJAUSEUSERNAME", nullable = false)
    @NotNull(message = "{NotNull.AUser.Username.Validation}")
    @Size(min = 5, max = 64, message = "{Size.AUser.Username.Validation}")
    private String username;
    @Column(name = "XPRJAUSEPASSWORD", nullable = false)
    @NotNull(message = "{NotNull.AUser.Password.Validation}")
    @Size(min = 5, max = 64, message = "{Size.AUser.Password.Validation}")
    private String password;
    @Column(name = "XPRJAUSENAME", nullable = false)
    @NotNull(message = "{NotNull.AUser.Name.Validation}")
    @Size(min = 2, max = 100, message = "{Size.AUser.Name.Validation}")
    private String name;
    @Column(name = "XPRJAUSESURNAME", nullable = false)
    @NotNull(message = "{NotNull.AUser.Surname.Validation}")
    @Size(min = 2, max = 100, message = "{Size.AUser.Surname.Validation}")
    private String surname;
    @Version
    @Column(name = "XPRJAUSEVERSION", nullable = false)
    private Long version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATASTAMP", nullable = false)
    private Date datastamp;
    @Column(name = "LOGIN")
    private Long login;
    @Column(name = "ACTION")
    private Long action;
}
