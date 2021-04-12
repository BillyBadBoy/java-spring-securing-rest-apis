package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "authorities")
public class UserAuthority {
    @Id
    private UUID id;

    @Column
    private String authority;

    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private User user;

    public UserAuthority() {}

    public UserAuthority(User user, String authority) {
        id = UUID.randomUUID();
        this.authority = authority;
        this.user = user;
    }

    public UserAuthority(UserAuthority userAuthority) {
        this.id = userAuthority.id;
        this.authority = userAuthority.authority;
        this.user = userAuthority.user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
