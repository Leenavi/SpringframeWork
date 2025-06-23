package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@RequiredArgsConstructor //생성자주입(autowired 대신)
public class BoardServiceImpl implements BoardService{

	//@RequiredArgsConstructor + final 해야함
	private final BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		log.info("register..................."+board);
		mapper.insertSelectKey(board);
		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get.....................");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..........................");
		return mapper.update(board) == 1;
		//update 성공시 1==1이므로 true 리턴. 실패시 0 == 1 틀리므로 false 리턴
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove..........................");
		return mapper.delete(bno) == 1;
		//update 성공시 1==1이므로 true 리턴. 실패시 0 == 1 틀리므로 false 리턴
	}

	@Override
	public List<BoardVO> getList(Criterial cri) {
		log.info("getList..............................");
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criterial cri) {
		
		return mapper.getTotalCount(cri);
	}
	
}
