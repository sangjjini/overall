package com.example.spring_project.controller;

import com.example.spring_project.domain.chat.Chat;
import com.example.spring_project.domain.chat.ChatRepository;
import com.example.spring_project.domain.chat.ChatRequestDto;
import com.example.spring_project.domain.chat.ChatResponseDto;
import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @PostMapping("chat/{no}/send")
    public void sendChat(WebRequest request, @PathVariable long no, @RequestBody ChatRequestDto chatRequestDto){
        //        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String log = "kevin@gmail.com";
        Member member = memberRepository.findByEmail(log);
        chatRequestDto.setNickname(member.getNickname());
        chatRequestDto.setEmail(log);
        chatRequestDto.setSquadNo(no);
        chatRepository.save(new Chat(chatRequestDto));
    }

    @GetMapping("chat/{no}")
    public List<ChatResponseDto> getChat(@PathVariable long no){
        List<ChatResponseDto> list = new ArrayList<>();
        List<Chat> chats = chatRepository.findAllBySquadNo(no);
        for(int i=0; i<chats.size(); i++){
            ChatResponseDto chatResponseDto = new ChatResponseDto(chats.get(i));
            list.add(chatResponseDto);
        }
        return list;
    }
}
