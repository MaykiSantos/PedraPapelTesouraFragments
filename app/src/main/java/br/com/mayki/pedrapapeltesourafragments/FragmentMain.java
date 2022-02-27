package br.com.mayki.pedrapapeltesourafragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import br.com.mayki.pedrapapeltesourafragments.databinding.FragmentMainBinding;


public class FragmentMain extends Fragment {

    private FragmentMainBinding fragmentMainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);

        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentMainBinding.dedraIB.setOnClickListener(item->this.onClick(item));
        fragmentMainBinding.papelIB.setOnClickListener(item->this.onClick(item));
        fragmentMainBinding.tesouraIB.setOnClickListener(item->this.onClick(item));

    }


    public void onClick(View view) {
        if(fragmentMainBinding.tresJogadores.isChecked()) {
            comTresJogadores(view);
        }else {
            comDoisJogadores(view);
        }
    }


    private void comTresJogadores(View view){
        Integer jogadaUsuario = defineJogadaUsuario(view);
        defineImagem(jogadaUsuario, fragmentMainBinding.suaJogadaIV, fragmentMainBinding.suaJogadaLL);
        System.out.println("valor Jogada usuario: " + jogadaUsuario);

        Integer jogadaMaquina01 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina01, fragmentMainBinding.jogadaMaquina01IV, fragmentMainBinding.jogadaMaquina01LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina01);

        Integer jogadaMaquina02 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina02, fragmentMainBinding.jogadaMaquina02IV, fragmentMainBinding.jogadaMaquina02LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina02);

        //verifica ganhador
        Integer resultado01 = verificaSeOprimeiroGanhoudoSegundo(jogadaMaquina01, jogadaMaquina02);
        Integer resultado02 = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina01);
        Integer resultado03 = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina02);

        if(resultado01 == 3 && resultado02 ==1){
            fragmentMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado01 == 1 && resultado02 == 1){
            fragmentMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado01 == 0 && resultado03 == 1){
            fragmentMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado02 == 3 || resultado03 == 3){
            fragmentMainBinding.resultado.setText(R.string.empate);
        }else{
            fragmentMainBinding.resultado.setText(R.string.voce_perdeu);
        }
    }

    private void comDoisJogadores(View view){
        fragmentMainBinding.jogadaMaquina02LL.setVisibility(View.GONE);

        Integer jogadaUsuario = defineJogadaUsuario(view);
        defineImagem(jogadaUsuario, fragmentMainBinding.suaJogadaIV, fragmentMainBinding.suaJogadaLL);
        System.out.println("valor Jogada usuario: " + jogadaUsuario);

        Integer jogadaMaquina01 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina01, fragmentMainBinding.jogadaMaquina01IV, fragmentMainBinding.jogadaMaquina01LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina01);

        Integer resultado = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina01);

        switch (resultado){
            case 0:
                fragmentMainBinding.resultado.setText(R.string.voce_perdeu);
                break;
            case 1:
                fragmentMainBinding.resultado.setText(R.string.voce_ganhou);
                break;
            case 3:
                fragmentMainBinding.resultado.setText(R.string.empate);
                break;
        }
    }

    private Integer defineJogadaUsuario(View view) {
        if (fragmentMainBinding.dedraIB.equals(view)) {
            return 0;
        } else if (fragmentMainBinding.papelIB.equals(view)) {
            return 1;
        }

        return 2;
    }

    private void defineImagem(Integer jogada, ImageView imagem, LinearLayout campo) {
        switch (jogada){
            case 0:
                imagem.setImageResource(R.mipmap.pedra);
                break;
            case 1:
                imagem.setImageResource(R.mipmap.papel);
                break;
            case 2:
                imagem.setImageResource(R.mipmap.tesoura);
                break;
            default:
                break;
        }
        campo.setVisibility(View.VISIBLE);
    }

    private Integer sorteiaJogadaMaquina(){
        Random gerador = new Random(System.currentTimeMillis());
        return gerador.nextInt(3);
    }

    private Integer verificaSeOprimeiroGanhoudoSegundo(Integer j1, Integer j2){
        //verifica se o primeito ganhou do segundo e retorna 1 se verdadeiro 0 se falso e 3 se empate
        if(j1 == 0 && j1!=j2){
            switch (j2){
                case 1:
                    return 0;
                case 2:
                    return 1;
            }
        }else if(j1 == 1 && j1!=j2){
            switch (j2){
                case 0:
                    return 1;
                case 2:
                    return 0;
            }
        }else if(j1 == 2 && j1!=j2){
            switch (j2){
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
        }

        return 3;
    }

}