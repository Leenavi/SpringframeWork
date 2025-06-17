package org.sample.service;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.PostDTO;

public interface BoardService {

    // 게시글 등록
    void register(BoardVO board);

    // 게시글 1건 조회 → DTO로 반환해야 View에서 모든 정보 사용 가능
    PostDTO get(Long boardid);

    // 게시글 수정
    boolean modify(PostDTO dto);

    // 게시글 삭제
    boolean remove(Long boardid);

    // 검색 + 페이징 리스트
    List<BoardVO> getList(Criteria cri);

    // 전체 게시글 수
    int getTotal(Criteria cri);

    // 브랜드별 리스트
    List<BoardVO> getAppleList();
    List<BoardVO> getSamsungList();
    List<BoardVO> getSonyList();
    List<BoardVO> getOtherList();
}
