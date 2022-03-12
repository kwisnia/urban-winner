package com.github.kwisnia.urbanwinner;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.vcs.VcsDataKeys;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WhatTheCommit extends AnAction {
    public static final String URL = "http://whatthecommit.com/index.txt";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var commitMessageWindow = e.getData(VcsDataKeys.COMMIT_MESSAGE_CONTROL);
        try {
            var request = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .GET()
                    .build();
            var httpClient = HttpClient.newHttpClient();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assert commitMessageWindow != null;
            commitMessageWindow.setCommitMessage(response.body());
        } catch (Exception ignored) {
            assert commitMessageWindow != null;
            commitMessageWindow.setCommitMessage("I know what I am doing. Trust me.");
        }

    }
}
