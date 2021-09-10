package com.yoon.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.model.Board;
import com.yoon.blog.model.RoleType;
import com.yoon.blog.model.User;
import com.yoon.blog.repository.UserRepository;
import com.yoon.blog.service.BoardService;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private BoardService boardService;
	/*
	 @PostMapping("/dummy/join")
	 public String join(String username, String password, String email) {
		System.out.println("username"+username);
		System.out.println("password"+password);
		System.out.println("email"+email);
		 return"회원가입이 완료되었습니다.";			 
	 }
	 */	
	
	@Autowired //의존성 주입.
    private UserRepository userRepository;
    
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			
			return "해당아이디는 존재하지않습니다 삭제할 수 없습니다.";
		}
		
		
		return "삭제되었습니다 ";
	}
	
	
	
	
	//form양식이 아닌 json으로 전달. 스프링 부트의 messageconverter의 jackson라이브러리가 변환해서 자동으로 받아줌.
	//restcontroller를 사용하기 때문에 data형식으로 값이 넘어오기때문에 웹브라우저가 이해할 수 있도록 변환을 시켜줘야함.->json
	//하지만 스프링부트에는 messageconverter가 알아서 jackson라이브러리를 호출을 하여서 알아서 json타입으로 변환을 해준다.
	@Transactional //함수종료시 자동 commit이 됨.//UPDATE시 더티체킹.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		System.out.println("id  "+ id);
		System.out.println("email  "+reqUser.getEmail());
		System.out.println("password  "+ reqUser.getPassword());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});//이때 영속화.
		//reqUser.setId(id);이방식으로 할 경우에는 username null값이뜸..user는 꽉차있어서 거기는 null이 될 수가 없음.
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		//userRepository.save(user);더티체킹으로 굳이 save를 할 필요가 없다.
		return user;
		
	}
	
    
	/*유저의 정보를 보냄.insert*/
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id "+user.getId());
		System.out.println("username "+user.getUsername());
		System.out.println("password "+user.getPassword());
		System.out.println("email "+user.getEmail());
		System.out.println("role "+user.getRole());
		System.out.println("date "+user.getCreateDate());
		
		user.setRole(RoleType.USER);//enum의 RoleType의 선언해놓은 값을 고정하여서 넣어준다.
		userRepository.save(user);
		return"회원가입이 완료되었습니다(dummyJOIN)";
	}
	
	
	
	
	
	//http://localhost:8080/blog/dummy/user/5
	//findByid의 리턴타입이 optional이다. 왜냐하면 해당 아이디값을 리턴을 했는데 null경우나 혹시 모를 경우을 대비해서 
	//optinal로 해당객체를 감싸서 해줄테니까 null인지 아닌지 너가 판단을 해서 return을 해서 프로그래밍해.라느뜻...
	//findById(id).get()은 아예 null일 경우가 없다. 하지만 별로 안좋은방법 왜냐? 혹시모를 경우에 대해 대비가 안됨.
	/*id넘버에 따른 매핑.select*/
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
			
		});
		
		return user;


		}
	
	/*유저의 모든데이터를 받아옴.select*/
	@GetMapping("/dummy/users")
	public List<User> list() {
	
		return userRepository.findAll();
	
}	
	
	
	
	/*페이징을 이용한 유저 데이터를 받아옴.select*/
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
	Page<User> PagingUser = userRepository.findAll(pageable);
	
	List<User> users = PagingUser.getContent();
	//리스트로 리턴하자
	
	return 	PagingUser;
	}
	
	
	
	@GetMapping("/dummy/board")
	public Page index(Model model, @PageableDefault(size=3, sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		
		model.addAttribute("boards", boardService.글목록(pageable));
		Page<Board> board = boardService.글목록(pageable);
		return board;
	}
	
}
