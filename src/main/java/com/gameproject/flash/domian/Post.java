package com.gameproject.flash.domian;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob    // Long text
    private String content;

    private String writtenBy;

    @Builder
    public Post(String title, String content, Member member, String writtenBy) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.writtenBy = writtenBy;
    }

    public PostEditor.PostEditorBuilder toEditor(){
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(PostEditor postEditor, Member member, String writtenBy) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
        this.member = member;
        this.writtenBy = writtenBy;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void mappingUser(Member member) {
        this.member = member;
        member.mappingPost(this);
    }

}
