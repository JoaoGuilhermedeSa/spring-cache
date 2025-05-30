# Tibia Cache POC 🧙‍♂️🐉

Esta é uma prova de conceito (POC) de uso de **cache com Caffeine** em uma aplicação **Spring Boot com Kotlin**, inspirada no universo do **Tibia**.

## ⚙️ Tecnologias utilizadas

- Kotlin
- Spring Boot
- Spring Cache
- Caffeine Cache
- Maven

## 📦 Funcionalidade principal

Simula uma API que consulta informações de personagens do Tibia e utiliza cache para acelerar respostas subsequentes.

### Endpoints disponíveis

#### 🔍 Buscar personagem

```http
GET /characters/{name}
```

- Retorna informações fictícias de um personagem.
- Primeira chamada simula uma chamada demorada (~3s).
- As chamadas subsequentes usam o cache (resposta instantânea enquanto válido).

#### ♻️ Limpar cache manualmente

```http
DELETE /characters/{name}/evict
```

- Remove manualmente o cache do personagem informado.

## 💾 Exemplo de resposta

```json
{
  "name": "Ekzura",
  "vocation": "Elder Druid",
  "level": 154,
  "world": "Antica"
}
```

## 🧪 Como executar

### Pré-requisitos

- JDK 17+ instalado
- Maven instalado

### Passos

```bash
# Clone o repositório ou extraia o zip
cd spring-cache

# Build do projeto
./mvnw clean install

# Rodar a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

## ⏱️ Configuração do Cache

- **Duração**: 600 segundos após o último acesso (`expireAfterAccess`)
- **Tamanho máximo**: 100 entradas
- **Cache utilizado**: `characters`

## 🎮 Curiosidades

- As vocações e mundos são gerados aleatoriamente com base nas classes e servidores clássicos do Tibia.
- Simula um comportamento de API externa com delay programado.