package bd.edu.seu.ums.Security;

import bd.edu.seu.ums.Entity.Permission;
import bd.edu.seu.ums.Entity.Role;
import bd.edu.seu.ums.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {


    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("active: "+user.isActive());

        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : user.getRoles()) {
            System.out.println("role: "+role.getRole());
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
            list.add(simpleGrantedAuthority);
            for (Permission permission : role.getPermissions()){
                SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(permission.getPermission());
                list.add(simpleGrantedAuthority1);
            }
        }
        this.authorities = list;
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
