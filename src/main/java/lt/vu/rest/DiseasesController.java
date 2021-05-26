package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Disease;
import lt.vu.persistence.DiseasesDAO;
import lt.vu.rest.contracts.DiseaseDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/disease")
public class DiseasesController {
    @Inject
    @Setter @Getter
    private DiseasesDAO diseasesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){
        Disease disease = diseasesDAO.findOne(id);
        if(disease == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setName(disease.getName());
        diseaseDto.setDescription(disease.getDescription());

        return Response.ok(diseaseDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer diseaseId, DiseaseDto diseaseDto){
        try{
            Disease existingDisease = diseasesDAO.findOne(diseaseId);
            if(existingDisease == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingDisease.setName(diseaseDto.getName());
            existingDisease.setDescription(diseaseDto.getDescription());

            diseasesDAO.update(existingDisease);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(DiseaseDto diseaseDto){
        try{
            Disease newDisease = new Disease();
            newDisease.setName(diseaseDto.getName());
            newDisease.setDescription(diseaseDto.getDescription());

            diseasesDAO.persist(newDisease);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/lock/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response Locking(@PathParam("id") final Integer diseaseId, DiseaseDto diseaseDto){
        try{
            Disease existingDisease = diseasesDAO.findOne(diseaseId);
            if(existingDisease == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            String name = existingDisease.getName();
            try{
                Thread.sleep(6000);
            }
            catch(Exception e){
                System.out.println(e);
            }
            existingDisease.setName(name+diseaseDto.getName());
            existingDisease.setDescription(diseaseDto.getDescription());

            diseasesDAO.update(existingDisease);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/error/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void Erroring(@PathParam("id") final Integer diseaseId, DiseaseDto diseaseDto){
        System.out.println("Console is: @@@@@@@@@@@@@@@@@@@@@@@@@");
        try{
            Disease existingDisease = diseasesDAO.findOne(diseaseId);
            existingDisease.setName(diseaseDto.getName()+"a");
            existingDisease.setDescription(diseaseDto.getDescription()+"a");

            diseasesDAO.update(existingDisease);
        } catch (OptimisticLockException e){
        }
    }
}