package org.sample.service;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        log.info("register......" + board);
        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long boardid) {
        log.info("get......" + boardid);
        return mapper.read(boardid);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......");
        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long boardid) {
        log.info("remove......" + boardid);
        return mapper.delete(boardid) == 1;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("getList.....");
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("getTotal............... ");
        return mapper.getTotalCount(cri);
    }

    @Override
    public List<BoardVO> getAppleList() {
        return mapper.getListByBrand("애플");
    }

    @Override
    public List<BoardVO> getSamsungList() {
        return mapper.getListByBrand("삼성");
    }

    @Override
    public List<BoardVO> getSonyList() {
        return mapper.getListByBrand("소니");
    }

    @Override
    public List<BoardVO> getOtherList() {
        return mapper.getListByBrand("LG");
    }
}
