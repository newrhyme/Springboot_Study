package com.study.Board.domain.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
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

    // 게시글 업데이트
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params) {
        postService.updatePost(params);
        return "redirect:/post/list.do";
    }

    // 게시글 삭제 (논리적 삭제)
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id) {
        postService.deletePost(id);
        return "redirect:/post/list.do";
    }
}