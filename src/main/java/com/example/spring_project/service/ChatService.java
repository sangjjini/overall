package com.example.spring_project.service;

import com.example.spring_project.domain.chat.Chat;
import com.example.spring_project.domain.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void deleteChat(long no){ chatRepository.deleteById(no);}
}
