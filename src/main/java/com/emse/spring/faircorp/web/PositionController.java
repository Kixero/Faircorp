package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.model.Pair;
import com.emse.spring.faircorp.model.PairDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("api/location")
@Transactional
public class PositionController
{
    final int N = 20;
    int counter = 0;

    @GetMapping
    public PairDto findAll()
    {
        int xmin = 10;
        int xmax = 24;
        int ymin = 12;
        int ymax = 20;
        double sigma = 0.5;
        ArrayList<PairDto> topline = new ArrayList<>();
        ArrayList<PairDto> rightline = new ArrayList<>();
        ArrayList<PairDto> bottomline = new ArrayList<>();
        ArrayList<PairDto> leftline = new ArrayList<>();

        ArrayList<PairDto> positions = new ArrayList<>();


        ArrayList<Double> hor_positions = linspace(xmin, xmax);
        ArrayList<Double> ver_positions = linspace(xmin, xmax);

        for (int k = 0; k < N; k++)
        {
            bottomline.add(random_position(hor_positions.get(k), ymin, sigma));
            rightline.add(random_position(xmax, ver_positions.get(k), sigma));
            topline.add(random_position(hor_positions.get(N - 1 - k), ymax, sigma));
            leftline.add(random_position(xmin, ver_positions.get(N - 1 - k), sigma));
        }

        positions.addAll(bottomline);
        positions.addAll(rightline);
        positions.addAll(topline);
        positions.addAll(leftline);

        counter = (counter ++) % 4 * N;

        return positions.get(counter);
    }

    public PairDto random_position(double x, double y, double sigma)
    {
        Random r = new Random();
        x = x + r.nextGaussian();
        y = y + r.nextGaussian();

        return new PairDto(x, y);
    }

    private ArrayList<Double> linspace(int xmin, int xmax)
    {
        ArrayList<Double> res = new ArrayList<>();

        for (int k = 0; k < N; k++)
        {
            res.add((double) (xmax - xmin) / (double) N * k);
        }
        return res;
    }
}
