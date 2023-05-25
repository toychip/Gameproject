package com.gameproject.flash.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.gameproject.flash.domian.Game;
import com.gameproject.flash.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
