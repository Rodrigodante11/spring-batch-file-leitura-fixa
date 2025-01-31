package com.rodrigo.arquivolargurafixajobapplication.reader;

import com.rodrigo.arquivolargurafixajobapplication.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoLarguraFixaReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> leituraArquivoLarguraFixaReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes ) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoLarguraFixaReader")
                .resource(arquivoClientes)
                .fixedLength() // Larguras Fixas
                .columns(new Range[] {
                    new Range(1,10),
                    new Range(11,20),
                    new Range(21,33),
                    new Range(34,43)
                })
                .names( new String[] {"nome", "sobrenome", "idade" , "email"})
                .targetType(Cliente.class)
                .build();
    }
}

