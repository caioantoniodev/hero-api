echo '{"name": "Homem Aranha", "power": "Sentido aranha", "alignment":"HERO"}' | kcat -P -b localhost:9092 -t HERO_CREATE_EVENT