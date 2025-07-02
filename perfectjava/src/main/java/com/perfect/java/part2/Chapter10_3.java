package com.perfect.java.part2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Chapter10_3 {

    static void parallelStreamExample() {
        // 以前のコードを下記に書き換えるだけで内部的に並列処理になります
        List.of("abc", "xyz", "za", "defghi").parallelStream()
                .filter((String s) -> {
                    return s.contains("a");
                })
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // 非並列ストリームから並列ストリームへの変換
        Stream<String> paraStream = Stream.of("abc", "xyz", "za", "defghi").parallel();

        // 並列ストリームから非並列ストリームへの変換
        Stream<String> stream = paraStream.sequential();
    }

    static void optionalObjExample() {
        // OptionalオブジェクトにくるまれるStringオブジェクト
        String s = "abc";

        // ofメソッドで生成
        Optional<String> os = Optional.of(s);

        // ofNullableメソッドで生成
        Optional<String> os2 = Optional.ofNullable(s);

        // nullから空Optionalオブジェクトを生成可能
        Optional<String> oEmpty = Optional.ofNullable(null);

        // 明示的に空Optionalオブジェクトを生成可能
        Optional<String> oEmpty2 = Optional.empty();
    }

    // Optionalオブジェクトを返すメソッド
    static Optional<String> method(boolean arg) {
        if (arg) {
            // 結果ありを模倣
            return Optional.of("success output");
        } else {
            // 結果なしを模倣
            return Optional.empty();
        }
    }

    // 上記コードの使用例
    static void optionalBasic() {
        Optional<String> ret = method(true);
        ret.ifPresentOrElse((String s) -> {
            System.out.println(s);
        }, () -> {
            System.out.println("no result");
        });
    }

    // nullチェックが連続するメソッド
    static void showPersonFirstName(Person person) {
        if (person != null) {
            Name name = person.name();
            System.out.println(name);
            if (name != null) {
                String firstName = name.firstName();
                if (firstName != null) {
                    System.out.println(firstName);
                }
            }
        }
    }

    // Optionalオブジェクトで上記コードを書き換え
    // ifの条件分岐がなくなったメソッド
    static void showPersonFirstNameOptional(Optional<OptionalPerson> person) {
        person.flatMap(OptionalPerson::name)
                .flatMap(OptionalName::firstName)
                .ifPresent(System.out::println);
    }

    // if文の条件分岐がなくなったメソッド
    // 推奨
    static void showPersonFirstNameMap(Person person) {
        Optional.ofNullable(person)
                .map(Person::name)
                .map(Name::firstName)
                .ifPresent(System.out::println);
    }

    static void showPersonExample() {
        // 使用例
        showPersonFirstName(new Person(new Name("Taro", "Suzuki")));

        // 使用例
        showPersonFirstNameOptional(Optional
                .of(new OptionalPerson(Optional.of(new OptionalName(Optional.of("Taro"), Optional.of("Suzuki"))))));

        // 使用例
        showPersonFirstNameMap(new Person(new Name("Taro", "Suzuki")));
    }

    // 空Optionalを返却するメソッド
    static Optional<String> emptyMethod() {
        return Optional.empty();
    }

    static void emptyMethodExample() {
        // 使用例
        String ret1 = emptyMethod().orElse("default value");
        System.out.println(ret1);

        String ret2 = emptyMethod().orElseGet(() -> {
            return "default value";
        });
        System.out.println(ret2);

        Optional<String> ret3 = emptyMethod().or(() -> {
            return Optional.of("default value");
        });
        ret3.ifPresent((String s) -> {
            System.out.println(s);
        });
    }

    static void optionalGet() {
        // 非推奨
        Optional<String> os = Optional.ofNullable("abc");
        String s = os.get();
        System.out.println(s);

        Optional<String> osError = Optional.ofNullable(null);
        String sE = osError.get();
    }

    static void streamBuildExample() {
        int[] result = "012[abc]345".chars()
                .dropWhile((int c) -> {
                    return c != '[';
                    // 先頭の"["文字を飛ばした出力列に変換
                }).skip(1).takeWhile((int n) -> {
                    return n != ']';
                }).toArray();

        String s = new String(result, 0, result.length);
        System.out.println(s);

        // 上記コードをまとめた処理
        String sb = "012[abc]345".chars()
                .dropWhile((int c) -> {
                    return c != '[';
                    // 先頭の"["文字を飛ばした出力列に変換
                }).skip(1).takeWhile((int n) -> {
                    return n != ']';
                })
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(sb);

    }

    public static void main(String[] args) {
        streamBuildExample();
    }
}

// nullになりうるコンポーネントを持つレコードクラス
// コンストラクタでnullチェックをするほうが望ましい
record Person(Name name) {
}

record Name(String firstName, String lastName) {
}

// コンポーネントの型をOptional型にしたレコードクラス
record OptionalPerson(Optional<OptionalName> name) {
}

record OptionalName(Optional<String> firstName, Optional<String> lastName) {
}