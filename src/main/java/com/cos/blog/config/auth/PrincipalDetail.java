package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다. 즉 PrincipalDetail을 저장함. 그래서 User를 포함시켜야함.
@Getter // 유저 정보를 꺼내기 위해서
public class PrincipalDetail implements UserDetails{
	private User user; // 기본 정보를 가지고 있는 유저 , 콤포지션 형태

	public PrincipalDetail(User user) {
		this.user=user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
 // 계정이 만료되었는가? 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정의 권한을 리턴함
	// 타입이 Collection이여서 불편함
	// 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole();
			}
		});
		//collectors.add(()->{return "ROLE_"+user.getRole()});
		
		return collectors;
	}
	
	
}
