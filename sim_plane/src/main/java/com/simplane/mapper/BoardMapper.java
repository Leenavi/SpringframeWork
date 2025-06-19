package com.simplane.mapper;

import com.simplane.domain.BoardVO;
import com.simplane.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BoardMapper {

    public BoardVO read(Long boardid);

    public List<BoardVO> readAll();

    public void create(BoardVO board);

    public int update(BoardVO board);

    public int delete(Long boardid);

    public List<BoardVO> getListWithPaging(Criteria cri);

    public int getTotalCount(Criteria cri);
}
