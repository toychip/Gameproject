package com.gameproject.flash.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.gameproject.flash.domian.Game;
import com.gameproject.flash.repository.GameRepository;
import com.gameproject.flash.response.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final AmazonS3 s3;
    private final GameRepository gameRepository;


    public List<Game> getAllGames() {

        // 여기에서 각 게임의 이미지와 비디오를 S3에서 다운로드할 수 있습니다.
        // 하지만 이 방식은 매우 비효율적일 수 있습니다.
        // 대신 S3의 URL을 게임 엔티티에 저장하고 클라이언트가 URL로부터 직접 다운로드하도록 하는 것이 효율적일 수 있습니다.

        return gameRepository.findAll();
    }

    public GameResponse get(Integer id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "는 없는 id입니다."));
        return new GameResponse(game);
    }
}
