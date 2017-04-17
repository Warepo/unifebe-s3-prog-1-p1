# ip-to-binary

## Tabela de Funcionalidades

| Nº | Nome |
|:--:|:--|
| 1 | `null` |
| 2 | `null` |
| 3 | Inserir um IP comnúmeros e pontuação |
| 4 | Aceitar uma máscara de IP no seguinte formato: `10.2.3.1(\/\d+)?` e validá-la. |
| 5 | Calcular a rede a qual o IP fornecido pertence. |

## Observações

1. Um número IPv4 é formado por 4 octetos (ou 4 sequências de 8 bits) no formato X.X.X.X.
    - 192.168.0.2 = 11000000.10101000.00000000.00000010
2. A classe de um número IP é dada da seguinte maneira
    - Se os 2 primeiros bits do primeiro octeto forem:
        - `00` ou `01`, o IP é classe A com máscara padrão /8
        - `10`, o IP é classe B com máscara padrão /16
        - `11`, o IP é classe C com máscara padrão /24

## Compilando e rodando o código

```bash
C:\path\to\project\
$ mkdir build

C:\path\to\project\
$ javac -encoding utf8 ./source/*.java -d ./build

C:\path\to\project\
$ cd build

C:\path\to\project\build\
$ java ClassName
```
