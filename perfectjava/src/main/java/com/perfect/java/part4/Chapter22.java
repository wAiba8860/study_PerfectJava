package com.perfect.java.part4;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class Chapter22 {

    static void httpRequestExample() throws InterruptedException, IOException {
        // HttpRequest.Builderオブジェクトを作成
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/<>")) // アクセス先の指定
                .GET(); // HTTPメソッドをGETに指定

        // HttpRequestオブジェクトを作成
        HttpRequest request = builder.build();

        // デフォルト設定のHttpClientオブジェクトを作成
        HttpClient client = HttpClient.newHttpClient();

        // リクエストを送信し、レスポンスを受け取る
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // HTTPレスポンス情報の取得例
        // responseは、HttpResponseオブジェクト
        response.statusCode(); // HTTPレスポンスのステータスコードを取得
        response.body(); // HTTPレスポンスの本文を取得
    }

    public static void main(String... args) {
        try {
            httpRequestExample();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
