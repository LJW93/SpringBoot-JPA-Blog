package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 (DI)
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username,password,email 데이터를 가지고 요청함
	
//	public String join(String username, String password, String email) { // key =value 
//		System.out.println("username: " +username);
//		System.out.println("password:"+password);
	//	System.out.println("email"+email);
		
	//	userRepository.save(User);
	//	return "회원가입 완료";
//	}
	@PostMapping("/dummy/join")
	public String join(User user) { // key =value 
		System.out.println("username: " +user.getId());
	System.out.println("password:"+user.getPassword());
		System.out.println("email"+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
		

	}
	//{id}주소로 파라메터를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id ) {
		// 못찾을시에 null , return null 방지하기 위해서 Optional로 User 객체를 감싸서 가져옴 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id" +id);
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 변환 ( 웹 브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter가 응답시에 자동 작동
		return user;
	}
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
//	@GetMapping("/dummy/page/{page}")
//	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC)pageable pagealbe) {
//		Page<User> users = userRepository.findAll(pageable);
//	}
	@Transactional //  더티 체킹 함수 종료시에 자동 commic이 됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody  User requestUser) {
		System.out.println("id:"+id);
		System.out.println("password" +requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user =userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//requestUser.setId(id);
		//requestUser.setUsername("dog");
		//userRepository.save(requestUser);
		return user;  
		
	}
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다 id :"+id;
		
	}
}
