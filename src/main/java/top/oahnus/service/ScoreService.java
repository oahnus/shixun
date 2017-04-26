package top.oahnus.service;

import top.oahnus.dto.Page;
import top.oahnus.dto.ScoreDto;
import top.oahnus.entity.Score;

import java.util.List;

/**
 * Created by oahnus on 2017/4/25
 * 14:07.
 */
public interface ScoreService {
    Page<List<Score>> selectScoreByParam(String courseId,String studentId, Integer page, Integer limit);

    Integer updateScore(ScoreDto scoreDto);
}
