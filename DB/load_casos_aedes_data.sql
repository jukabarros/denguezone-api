LOAD DATA LOCAL INFILE '/home/thais/programacaoAplicada/CSVs/casos-dengue2015_refinado.csv'
INTO TABLE casos_aedes FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n' IGNORE 1 LINES (
nu_notificacao,
tp_notificacao,
agravo,
dt_notificacao,
ds_semana_notificacao,
notificacao_ano,
dt_diagnostico_sintoma,
ds_semana_sintoma,
dt_nascimento,
sexo,
tp_gestante,
tp_raca_cor,
tp_escolaridade,
co_uf_residencia,
co_municipio_residencia,
co_distrito_residencia,
co_bairro_residencia,
nome_logradouro_residencia,
nu_residencia,
nu_cep_residencia,
tp_zona_residencia,
tp_classificao_final,
tp_criterio_confirmacao,
tp_evolucao_caso,
st_ocorreu_hospitalizacao
);
