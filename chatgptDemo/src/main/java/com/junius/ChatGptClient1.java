package com.junius;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

/**
 * @author junius
 * @date 2023/05/27 09:40
 * @project codeHub
 **/
public class ChatGptClient1 {
    public static final String KEY = "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";

    public static void main(String[] args) {
        String info = "能不能帮我写一首诗";
        toGpt(info);
    }

    private static void toGpt(String info) {
        //注意：参数2用于设置超时时间
        OpenAiService service = new OpenAiService(KEY,20000);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003") //使用的模型
                .prompt(info) //生成提示
                .temperature(0D) //创新采样
                .maxTokens(1000) //Token大小设置
                .topP(1D) //情绪采样。[0,1]:从悲观到乐观
                .frequencyPenalty(0D) //频率处罚系数。用来设置文本中出现重复词汇时的处罚参数
                .presencePenalty(0D) //重复处罚系数
                .build();
        service.createCompletion(completionRequest)
                .getChoices()
                .forEach(System.out::println);
    }
}
