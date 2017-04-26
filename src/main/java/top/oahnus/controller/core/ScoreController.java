package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.ScoreDto;
import top.oahnus.entity.Score;
import top.oahnus.enums.ServerState;
import top.oahnus.service.ScoreService;

import java.util.List;

/**
 * Created by oahnus on 2017/4/25
 * 12:08.
 */
@RestController
@CrossOrigin
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public ResponseData<Page> selectScoreByParam(@RequestParam(value = "courseId",required = false)String courseId,
                                                 @RequestParam(value = "studentId",required = false)String studentId,
                                                 @RequestParam("page")Integer page,
                                                 @RequestParam("limit")Integer limit) {
        Page<List<Score>> p = scoreService.selectScoreByParam(courseId, studentId, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @PostMapping
    public ResponseData<Integer> changeScore(@RequestBody @Validated ScoreDto scoreDto) {
        Integer count = scoreService.updateScore(scoreDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }
}
