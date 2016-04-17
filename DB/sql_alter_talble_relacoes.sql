ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_notificacao)
REFERENCES aedes_zone.tipo_notificacao(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (co_agravoagravoagravo)
REFERENCES aedes_zone.agravo(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_gestante)
REFERENCES aedes_zone.tipo_gestante(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_raca_cor)
REFERENCES aedes_zone.raca_cor(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_escolaridade)
REFERENCES aedes_zone.escolaridade(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (co_municipio_residencia)
REFERENCES aedes_zone.municipio_residencia(codigo);


ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (co_uf_residencia)
REFERENCES aedes_zone.uf_residencia(codigo);


ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (co_bairro_residencia)
REFERENCES aedes_zone.bairro_residencia(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (co_distrito_residencia)
REFERENCES aedes_zone.distrito_residencia(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_evolucao_caso)
REFERENCES aedes_zone.evolucao_caso(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_criterio_confirmacao)
REFERENCES aedes_zone.criterio_confirmacao(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_classificao_final)
REFERENCES aedes_zone.classificacao_final(codigo);

ALTER TABLE aedes_zone.casos_aedes
ADD FOREIGN KEY (tp_zona_residencia)
REFERENCES aedes_zone.zona_residencia(codigo);
