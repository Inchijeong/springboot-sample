package org.zerock;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.WebBoard;
import org.zerock.domain.WebReply;
import org.zerock.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTests {

	@Autowired
	WebReplyRepository repo;
	
	@Test
	public void insertRepliesDummies() {
		
		Long[] arr = {305L, 306L, 307L};
		
		Arrays.stream(arr).forEach(i -> {
			
			WebBoard board = new WebBoard();
			board.setBno(i);			
			
			IntStream.range(0, 10).forEach(j -> {
				
				WebReply reply = new WebReply();
				reply.setReplyText("REPLY ..." + j);
				reply.setReplyer("replyer" + j);
				reply.setBoard(board);
				
				repo.save(reply);
			});			
		});
		
	}
}
