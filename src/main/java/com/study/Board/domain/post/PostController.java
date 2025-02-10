package com.study.Board.domain.post;


import com.study.Board.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        // id가 있으면 기존 게시글 조회 -> model에 추가 (수정)
        if (id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);   // View에 "post"라는 이름으로 데이터 전달
        }
        return "post/write";    // write.html 템플릿 렌더링하면서 "post" 데이터를 Thymeleaf에서 사용할 수 있게 함.
    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    // 게시글 상세 정보
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    // 기존 게시글 업데이트
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        postService.updatePost(params);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제 (논리적 삭제)
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, Model model) {
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        postService.deletePost(id);
        return showMessageAndRedirect(message, model);
    }

    // 사용자에게 메시지 전달, 페이지 리다이렉트
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}