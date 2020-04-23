package dev.ceos.walkwith.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.ceos.walkwith.controller.vo.*;
import dev.ceos.walkwith.domain,.model.*;

@RequiredArgsConstructor
@Service

public class FriendService {
    private final FriendRepository friendRepository;

    public final UserRepository userRepository;

    public final AlarmRepository alarmRepository;

    //친구 검색
    /*
     * 친구검색  - > 검색 결과 없으면 error / 있으면
     * 친구 추가 - > 친구 검색결과 나온 사람 추가한다.  - > 양방향으로 둘다 추가된다
     *
     * 친구 전체 목록 조회 - > 친구 이름, total_caloring , exercising
     *
     * */
    @Transactional
    public FriendResponse findFriend(UserNameVO userNameVO) {
        FriendResponse friendOne = friendRepository.findFriend(userNameVO)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다."));

        return new friendOne;
    }

    /**
     * req body에 내id,친구id
     * 양방향으로 둘다 저장된다!
     * 친구 이름으로 검색하고 / 내 아이디랑 친구 이름이랑 같이 보내서
     *
     */
    @Transactional
    public ResponseVO createFriend( UserTwoVO userTwoVO ) {
        UserTwoVO aleadyFriend = friendRepository.aleadyCheck(userTwoVO);

        if(aleadyFriend != null) {
            return new ResponseVO("warning : aleady friend");
        }

        friendRepository.createFriend(userTwoVO);
        alarmRepository.saveFriendAlarm(userTwoVO);

        return new ResponseVO("success : create friend success");
    }

    /**
     * >양방향 삭제
     * 친구 삭제
     * 이벤트 코드 3번
     */
    @Transactional
    public ResponseVO delecteFriend( UserTwoVO userTwoVO ) {
        friendRepository.deleteFriend(userTwoVO);
        alarmRepository.saveFriendDelAlarm(userTwoVO);
        return new ResponseVO("success : delete friend success");
    }

    //친구 조회
    @Transactional
    public ListResponseVO Friends(UserInfoVO userInfoVO) {
        List<FriendResponse> friendsList = friendRepository.friends(userInfoVO);

        ListResponseVO listResponseVO = new ListResponseVO();
        listResponseVO.setResponse(friendsList);

        return listResponseVO;
    }


}