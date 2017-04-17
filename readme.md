# ip-to-binary

## Tabela de Funcionalidades

| Nº | Nome | Descrição |
|:--:|:--|:--|
| 1 | Inserir | pede o número de matricula, o nome do funcionário e coloca na lista |
| 2 | Retirar | retira o funcionário da lista e mostra o nome do mesmo |
| 3 | Mostrar | mostra o número de matricula de todos os funcionários |
| 4 | Onde está | pede o número de matricula e diz em que posição da fila está o funcionário (Não é a posição do array) |
| 5 | Quem é | pede o número de matricula e informa o nome deste funcionário |
| 6 | Cabeça | mostra o nome do funcionário que está na cabeça da fila |
| 7 | Detonar | destrói a lista |
| 8 | Quantos | diz quantos funcionários estão na lista |
| 9 | Vazar | Pica a mula! |

## Observações

1. Um número IPv4 é formado por 4 octetos (ou 4 sequências de 8 bits) no formato X.X.X.X.
 - 192.168.0.2 = 11000000.10101000.00000000.00000010
2. A classe de um número IP é dada da seguinte maneira
 - Se os 2 primeiros bits do primeiro octeto forem:
 -- ‘00’ ou ‘01’, o IP é classe A com máscara padrão /8
 -- ‘10’, o IP é classe B com máscara padrão /16
 -- ‘11’, o IP é classe C com máscara padrão /24

## Compilando e rodando o código

```bash
C:\path\to\project\
$ javac -encoding utf8 ./source/*.java -d ./build

C:\path\to\project\source\
$ cd ..\build

C:\path\to\project\build\
$ java ClassName
```
