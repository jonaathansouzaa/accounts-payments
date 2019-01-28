Desenvolver um Serviço Rest para tratar as regras de negócio descritas abaixo.
- Linguagem: Java
- Injeção de dependência
- Informações devem ser persistidas em um banco de dados relacional.
- O código deverá ser disponibilizado em uma drive (Google, DropBox) ou um repositório GIT

Diferenciais
A utilização dessas tecnologias será considerado um diferencial.

- Java 8
- Spring
- JPA
- Maven/ Gradle
- Liquibase / Flyway
- Junit
- Caso deseje a criação de um Front em AngularJs (O layout não será avaliado)

Serviços

* Inclusão de conta a pagar

	* Nome: Texto
	* Valor Original: Numeral
	* Data de Vencimento: Data
	* Data de Pagamento: Data

* Listagem das contas cadastradas
	
	* Nome: Texto
	* Valor Original: Numeral
	* Valor Corrigido: Numeral
	* Quantidade de dias de atraso: Numeral
	* Data de Pagamento: Data

Regras de Negócio

➔ Todos os campos são obrigatórios

➔ No cadastro de contas a pagar terá que verificar se a conta está em atraso, caso esteja será incluído a seguinte

Regra:


| Dias em atraso  | Multa         | Juros ao Dia  |
| -------------   | ------------- | ------------- |
| até 3 dias      | 2%            | 0.1%          |
| superior 3 dias | 3%            | 0.2%          |
| superior 5 dias | 5%            | 0.3%          |

➔ A quantidade de dias em atraso e a regra de para o cálculo devem ser persistidos.