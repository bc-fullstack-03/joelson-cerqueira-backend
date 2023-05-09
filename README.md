# Joelson-Cerqueira-Backend

* Perfil do DockerHub : joelson0935
#
## Guia para usar os endpoints abaixo : 

* AddFollower : {
* "id":"51a6c791-4afa-4ba3-aeb2-01506a8fad5d",
*  "user":{
*     "id": "4f5863f0-468e-4b97-95e3-30cafad94d23"
*    }
* }
* o primeiro id é a pessoa a ser seguida e o segundo id é a pessoa que vai seguir.
* a primeira pessoa será seguida pela segunda pessoa que fará parte da lista de 
* seguidores da primeira pessoa
#
#
* RemoveFollower : {
*  "id":"51a6c791-4afa-4ba3-aeb2-01506a8fad5d",
*  "user":{
*     "id": "4f5863f0-468e-4b97-95e3-30cafad94d23"
*    }
*  }
* o primeiro id é a pessoa que tem uma lista de seguidor e o segundo id é o seguidor
*  que deixará de seguir a primeira pessoa
#
#
*AddPhoto : {
*   "id": "c380110a-5dc0-41ba-9397-529633a6ef9c",
*   "photo": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqLumtHg2WSAgqFG9G1_bcZzaI-keauIBUAA&usqp=CAU"
* }
#
#
* CreateComment : {
*    "id": "f4042acf-d7dd-475a-9b9b-e60d916ac0a6",
*    "post": {
*    "text": "another comment finish here",
*    "user": {
*    "id": "1d048be5-5314-4212-a742-3f497b6a6377"
*        }
*    }
*  }
* o primeiro id pertence a postagem que será comentada, daí monta o post com a postagem
* e por fim coloca o id do usuário responsável pelo comentário.
#
#
* AddLike : {
*    "user": {
*    "id": "c380110a-5dc0-41ba-9397-529633a6ef9c"
*      }
*   }
*   o primeiro parâmetro é o id do post que ganhará o like, este parâmetro será adicionado
*    nos params: parâmetro: id, argumento: id do post alvo.
*   dentro do body será colocado o id do usuário que dará o like no post.
#
#
* RemoveLike : {
*       "id": "c380110a-5dc0-41ba-9397-529633a6ef9c"
*  }
*   o primeiro parâmetro é o id do post que perderá o like, este parâmetro será adicionado
*    nos params: parâmetro: id, argumento: id do post alvo.
*   dentro do body será colocado o id do like que deixará de existir.
#
#
* RemoveComment : {
*    "id": "1c133de5-8be5-43fc-9508-90cbf1154fb8",
*    "post": {
*        "id": "757950f5-ec8e-4d10-b92c-cb91694b9200"
*    }
* }
* o primeiro id pertence ao post alvo, daí o segundo id é do comentário que será apagado.
#
#
* TotalComments, TotalLikes : são dois endpoints que apenas devolvem o tamanho de cada 
* lista, a lista de comentários e a lista de likes.
#
#
* Os demais endpoints eu não irei descrever pois entendo que são endpoints padrão, 
* comuns de um crud simples e não precisam de explicação para serem testados.


