package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.BoardVO;
import org.zerock.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
//	@Autowired  //필드 주임
//	private BoardRepository boardRepository;
	
	
	//생성자 주입. 요즘 추세
	private final BoardRepository boardRepository;
	
	public List<BoardVO> selectListBoard() {
		
		return boardRepository.selectAllBoards();
	}
	
	public BoardVO selectOneByNum(int num) {
		return boardRepository.selectOneByNum(num);
	}
	
	public void insertBoard(BoardVO bVo) {
		boardRepository.insertBoard(bVo);
	}
	
	public void updateBoard(BoardVO bVo) {
		boardRepository.updateBoard(bVo);
	}
	
	public void deleteBoard(int num) {
		boardRepository.deleteBoard(num);
	}
	
	public void updateReadCount(int num) {
		boardRepository.updateReadCount(num);
	}
	
	
	
	public boolean checkPassword(int num, String pass) {
		BoardVO bVo = boardRepository.selectOneByNum(num);
		
		if(bVo.getPass().equals(pass)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean checkPassword2(int num, String pass) {
		BoardVO bVo = boardRepository.selectOneByNum(num);
		
		if(bVo.getPass().equals(pass)) {
			boardRepository.updateBoard(bVo);
			return true;
		}else {
			return false;
		}
	}
	
}
